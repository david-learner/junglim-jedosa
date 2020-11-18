function signUpSubmit() {
    console.log("called signUpSubmit()");
    let signUpForm = document.getElementById("sign-up-form");

    if(!checkSignUpFormValidation()) {
        console.log("회원가입 폼 정보가 유효하지 않습니다.");
        return false;
    }

    signUpForm.submit();
}

function checkSignUpFormValidation() {
    console.log("called checkSignUpFormValidation()");

    let email = document.getElementById("sign-up-email");
    let emailConfirmation = document.getElementById("sign-up-email-confirmation");
    let emailDuplication = document.getElementById("sign-up-email-duplication");
    let password = document.getElementById("sign-up-password");
    let passwordConfirmation = document.getElementById("sign-up-password-confirmation");
    let name = document.getElementById("sign-up-name");
    let phone = document.getElementById("sign-up-phone");
    let zipcode = document.getElementById("sign-up-address-zipcode");
    let address = document.getElementById("sign-up-address");
    let detailedAddress = document.getElementById("sign-up-detailed-address");
    let siteUsageAgreement = document.getElementById("site-usage-agreement");
    let personalInfoAgreement = document.getElementById("personal-info-agreement");

    // 공백 확인
    if (!siteUsageAgreement.checked) {
        alert("사이트 이용 약관 동의가 필요합니다");
        siteUsageAgreement.focus();
        return false;
    } else if (!personalInfoAgreement.checked) {
        alert("개인정보수집 동의가 필요합니다");
        personalInfoAgreement.focus();
        return false;
    } else if (email.value === "") {
        alert("이메일을 입력해주세요");
        email.focus();
        return false;
    } else if (emailConfirmation.value === "") {
        alert("이메일을 입력해주세요");
        email.focus();
        return false;
    } else if (password.value === "") {
        alert("비밀번호를 입력해주세요");
        password.focus();
        return false;
    } else if (passwordConfirmation.value === "") {
        alert("비밀번호를 입력해주세요");
        passwordConfirmation.focus()
        return false;
    } else if (name.value === "") {
        alert("이름를 입력해주세요");
        name.focus();
        return false;
    } else if (phone.value === "") {
        alert("연락처를 입력해주세요");
        phone.focus();
        return false;
    } else if (zipcode.value === "") {
        alert("우편번호를 입력해주세요");
        zipcode.focus();
        return false;
    } else if (address.value === "") {
        alert("주소를 입력해주세요");
        address.focus();
        return false;
    }
    // else if (detailedAddress.value === "") {
    //     alert("상세주소를 입력해주세요");
    //     detailedAddress.focus();
    //     return false;
    // }

    // 입력 값 확인
    if (email.value !== emailConfirmation.value) {
        alert("이메일이 일치하지 않습니다");
        email.focus();
        return false;
    } else if (emailDuplication.value !== 'false') {
        console.log("emailDuplication: " + emailDuplication.value);
        alert("이메일 중복 확인이 필요합니다")
        emailDuplication.focus();
        return false;
    } else if (password.value !== passwordConfirmation.value) {
        alert("비밀번호가 일치하지 않습니다");
        password.focus();
        return false;
    } else if (password.value.length < 8) {
        alert("비밀번호는 8자리 이상이어야 합니다")
        password.focus();
        return false;
    }

    return true;
}

function checkEmailDuplication() {
    let emailDuplication = document.getElementById("sign-up-email-duplication");
    let email = document.getElementById("sign-up-email").value;
    let emailData = {
        email: email
    }

    $.ajax({
        url: "/account/email/duplicate",
        data: emailData,
    }).done(function () {
        alert("사용 가능한 이메일입니다")
        emailDuplication.value = false;
    }).fail(function (jqXHR, textStatus, errorThrown) {
        alert(jqXHR.responseText);
        emailDuplication.value = true;
    });
}
// todo email 중복확인 서버측에서 검증 후 응답 뿌려주기

