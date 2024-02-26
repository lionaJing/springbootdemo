package com.lemon.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Shuai.Jing
 * @date 2023/7/20
 */
public class PDFUtils {

    private final static String PROJECT_NAME = "springboot-controller/src/main/resources/static";
    private static File PROJECT_REPORT_NAME;

    static {
        PROJECT_REPORT_NAME = new File(PROJECT_NAME, "report");
        if(!PROJECT_REPORT_NAME.exists()) {
            PROJECT_REPORT_NAME.mkdirs();
        }
        System.out.println("exists: " + PROJECT_REPORT_NAME.exists());
        System.out.println("isDirectory: " + PROJECT_REPORT_NAME.isDirectory());
        System.out.println("getName: " + PROJECT_REPORT_NAME.getName());
    }

    /**
     * 生成报告的文件夹
     *
     * @param userId  装维人员登录工号
     * @param account 客户联系电话
     * @return File
     */
    public static File getReportFile(String userId, String account) {
        File file = new File(PROJECT_REPORT_NAME, userId + File.separator + account);
        System.out.println("exists: " + file.exists());
        if (!file.exists()) {
            System.out.println("mkdirs");
            file.mkdirs();
        }
        return file;
    }

    /**
     * img 转 pdf
     *
     * @param imgPath 图片路径
     * @param pdfName pdf名字
     * @return File PDF文件
     */
    public static File imgToPdf2(String imgPath, String parentPath, String pdfName) {
        File pdfFile = new File(parentPath, pdfName.concat(".pdf"));
        if (pdfFile.exists()) {
            pdfFile.delete();
        }
        Document document = null;
        FileOutputStream fo = null;
        try {
            Rectangle pageA4 = PageSize.A4;
            float widthA4 = pageA4.getWidth();
            float heightA4 = pageA4.getHeight();

            Image img = Image.getInstance(imgPath);
            img.setAlignment(Image.MIDDLE | Image.UNDERLYING);
            float imgWidth = img.getWidth();
            float imgHeight = img.getHeight();

            float scale = widthA4 / imgWidth;
            img.scaleToFit(imgWidth * scale, imgHeight * scale);

            Rectangle rectangle = new Rectangle(widthA4 + 20, imgHeight * scale + 20);
            document = new Document(rectangle, 10, 10, 10, 10);

            fo = new FileOutputStream(pdfFile);
            PdfWriter.getInstance(document, fo);

            document.open();
            document.newPage();
            document.add(img);

            pdfFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
            return pdfFile;
        } finally {
            document.close();
            if (fo != null) {
                try {
                    fo.flush();
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return pdfFile;
    }

    /**
     * 给PDF添加水印
     * 注意： 添加中文水印需要字体支持
     *
     * @param inputPDFFile     输入的PDF文件
     * @param outputPDFFile    输出的PDF文件
     * @param waterMarkContent 水印内容
     * @return
     */
    public static String pdfAddWaterMark(String inputPDFFile, String outputPDFFile, String waterMarkContent) {
        try {
            // 水印的高和宽
            int waterMarkHeight = 20;
            int watermarkWeight = 30;
            // 水印间隔距离
            int waterMarkInterval = 170;

            // 读取PDF文件流
            PdfReader pdfReader = new PdfReader(inputPDFFile);
            // 创建PDF文件的模板，可以对模板的内容修改，重新生成新PDF文件
            PdfStamper pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(outputPDFFile));
            // 设置水印字体
            Font font = FontFactory.getFont(FontFactory.COURIER, 20, new BaseColor(150, 199, 234));

            // 设置PDF内容的Graphic State 图形状态
            PdfGState pdfGraPhicState = new PdfGState();
            // 填充透明度
            pdfGraPhicState.setFillOpacity(0.2f);
            // 轮廓不透明度
            pdfGraPhicState.setStrokeOpacity(0.4f);

            // PDF页数
            int pdfPageNum = pdfReader.getNumberOfPages() + 1;

            // PDF文件内容字节
            PdfContentByte pdfContent;

            // PDF页面矩形区域
            Rectangle pageRectangle;
            for (int i = 1; i < pdfPageNum; i++) {
                // 获取当前页面矩形区域
                pageRectangle = pdfReader.getPageSizeWithRotation(i);
                pdfContent = pdfStamper.getOverContent(i);

                // 获取当前页内容，getOverContent表示之后会在页面内容的下方加水印
                // pdfContent = pdfStamper.getUnderContent(i);

                pdfContent.saveState();
                // 设置水印透明度
                pdfContent.setGState(pdfGraPhicState);

                pdfContent.beginText();
                pdfContent.setFontAndSize(font.getBaseFont(), 20);
                pdfContent.setColorFill(new BaseColor(76, 151, 204));

                for (int height = waterMarkHeight; height < pageRectangle.getHeight(); height = height + waterMarkInterval) {
                    for (int width = watermarkWeight; width < pageRectangle.getWidth() + watermarkWeight;
                         width = width + waterMarkInterval) {
                        pdfContent.showTextAligned(Element.ALIGN_LEFT, waterMarkContent, width - watermarkWeight,
                                height - waterMarkHeight, 45);
                    }
                }
                pdfContent.endText();
            }
            pdfStamper.close();
            pdfReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }
}
