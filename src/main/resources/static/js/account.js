function loginSubmit() {
    let loginForm = document.getElementById("login-form");
    let email = document.getElementById("login-email");
    let password = document.getElementById("login-password");

    if (email.value === "") {
        alert("이메일을 입력해주세요")
        email.focus();
        return false;
    } else if (password.value === "") {
        alert("비밀번호를 입력해주세요")
        password.focus();
        return false;
    }

    let loginData = {
        email: email.value,
        password: password.value
    }

    $.ajax({
        url: "/login",
        type: 'POST',
        data: loginData,
        contentType: "application/x-www-form-urlencoded"
    }).done(function (data, textStatus, jqXHR) {
        console.log(jqXHR.getResponseHeader("location"));
        window.location = jqXHR.getResponseHeader("location");
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
    });
}