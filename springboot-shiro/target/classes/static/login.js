/**
 * @author Shuai.Jing
 * @date 2019/4/11
 */
var vue = new Vue({
    el: '#app',
    data:{
        username: 'admin',
        password:'123456'
    },
    methods:{
        login:function () {
            toLogin()
        }
    }
})
function toLogin() {
    var obj = {
        "username": vue.username,
        'pwd': vue.password
    }
    $.ajax({
        url: "http://localhost:8080/login",
        type: 'POST',
        data: obj,
        success:function (data) {
            //obj
            //{"result":{"code":0,"desc":"ok"},"data":"admin"}
            console.log(typeof data)
            console.log(JSON.stringify(data))
        },
        error:function () {
            alert('登录失败')
        }
    })
}

