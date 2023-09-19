
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
    const response = await fetch(`${baseUrl}/login`, {
        method: "POST",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err.message))

    const responseArr = await response.json()

    if(responseArr[0] === "Username or password incorrect"){
    alert("Username or password incorrect")}

    else if (responseArr[0] === "http://localhost:8080/home.html"){
//        window.location.replace("http://localhost:8080/home.html")
        window.location.replace("http://localhost:63342/java-proj-4%203/noteApp/static/home.html")

        document.cookie = `userId=${responseArr[1]}`;
//        document.cookie = `username=${responseArr[2]}`;
    }

}

registerForm.addEventListener("submit", handleSubmit)