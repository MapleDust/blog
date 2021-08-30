//base64业务类
let base64App = new Vue({
    el: "#base64",
    data: {
        text: null,
        base64: null,
        base64HasError: false,
        base64Error: null
    },
    methods: {
        base64Encryption: function () {
            let data = {
                "text": base64App.text
            }
            $.ajax({
                url: "/base64Encryption",
                data: data,
                success: function (json) {
                    //如果状态码为200
                    if (json.state === 200) {
                        //将错误删除
                        base64App.base64HasError = false;
                        //将加密过后的文本返回给用户
                        base64App.base64 = json.data;
                    } else {
                        //设置错误信息
                        base64App.base64Error = json.message;
                        //将错误信息显示给用户
                        base64App.base64HasError = true;
                    }
                }
            });
        },
        base64Decryption: function () {
            let data = {
                "base64": base64App.base64
            }
            $.ajax({
                url: "/base64Decryption",
                data: data,
                success: function (json) {
                    if (json.state === 200) {
                        //将错误删除
                        base64App.base64HasError = false;
                        //将解密的文本返回给用户
                        base64App.text = json.data;
                    } else {
                        //设置错误信息
                        base64App.base64Error=json.message;
                        //将错误信息显示给用户
                        base64App.base64HasError=true;
                    }
                }
            });
        }
    }
});