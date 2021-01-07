const form = document.querySelector(".form")
const error = document.getElementById("error")
const username = document.getElementById("username")
const email = document.getElementById("email")
const fName = document.getElementById("fName")
const lName = document.getElementById("lName")
const password = document.getElementById("password")
const cPassword = document.getElementById("cPassword")
function submitForm(){
    if(isValid()){
        form.submit()
    }
}
function isValid() {
    let isValid = false
    if(!username.value){
        error.innerText = "ERROR: Must include username"
    } else if(!validateEmail(email.value)){
        error.innerText = "ERROR: Must include valid email"
    } else if(!fName.value){
        error.innerText = "ERROR: Must include first name"
    } else if(!lName.value){
        error.innerText = "ERROR: Must include last name"
    } else if(!password.value.length){
        error.innerText = "ERROR: Must include password"
    } else if(!cPassword.value){
        error.innerText = "ERROR: Must include 'confirm password'"
    } else if(password.value.length <= 2){
        error.innerText = "ERROR: Password length must be greater than 2"
    } else if(password.value != cPassword.value){
        error.innerText = "ERROR: Passwords do not match"
    } else {
        error.innerText = ""
        isValid = true
    }
    return isValid
}
function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}