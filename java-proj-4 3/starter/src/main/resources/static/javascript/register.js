
const registerForm = document.getElementById('form')
const registerUsername = document.getElementById('username')
const registerPassword = document.getElementById('password')

const headers = {
    'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080/users'


const handleSubmit = async (e) =>{
    e.preventDefault()

    let bodyObj = {
        username: registerUsername.value,
        password: registerPassword.value
    }
    console.log(bodyObj)
    const response = await fetch(`${baseUrl}/register`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()

    console.log(response)
    if(responseArr[0] === "http://localhost:8080/login.html"){
        window.location.replace("http://localhost:63342/java-proj-4%203/noteApp/static/login.html")
    }
    else if(response.redirected == false){
        alert("Username is already taken")
    }


}

registerForm.addEventListener("submit", handleSubmit)