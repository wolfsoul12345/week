function confirm_pass(){
    var password = document.getElementById("password").value ;
    var confirm_password = document.getElementById("confirm_password").value;
    if (password !== confirm_password ) {
        document.getElementsByClassName("message")[0].style.display="block";
        document.getElementById("register").setAttribute("disabled", true);
    }
    else {
        document.getElementsByClassName("message")[0].style.display = "none";
        document.getElementById("register").removeAttribute("disabled");
    }
}
function user_exist() {
    $.ajax({
        type:"get",
        url:"user_exist?userName="+document.getElementById("username").value,
        success:function (e) {
            console.log(e)
            document.getElementsByClassName("user_exist")[0].innerHTML=e.msg;
            if (e.msg==='用户名已存在') document.getElementsByClassName("user_exist")[0].style.color="red";
            else document.getElementsByClassName("user_exist")[0].style.color="green";

        }
        }
    )
}