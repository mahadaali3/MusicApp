const musicContainer = document.getElementById("music-container");
const title = document.getElementById("title-input");
const artist = document.getElementById("artist-input");
const genre = document.getElementById("genre-input");
const year = document.getElementById("year-input");
const form = document.getElementById("music-form");
const table = document.getElementById("table");
const updateTitle = document.getElementById("updateTitle");
const updateArtist = document.getElementById("updateArtist");
const updateGenre = document.getElementById("updateGenre");
const updateYear = document.getElementById("updateYear");
const editTable = document.getElementById("editTable");

const cookieArr = document.cookie.split("=");
console.log(cookieArr);
console.log("hello");
//
//const cookieArr = document.cookie.split("=");
//
//console.log(cookieArr)
//console.log("hello")



const headers = {
    'Content-Type':'application/json'
}

const baseUrl = 'http://localhost:8080/music'

//const handleSubmit = async (e) =>{
//    e.preventDefault()
//
//    let bodyObj = {
//        title: title.value,
//        artist: artist.value,
//        genre: genre.value,
//        year: year.value
//    }
//    console.log(bodyObj)
//    const response = await fetch(`${baseUrl}`, {
//        method: "POST",
//        body: JSON.stringify(bodyObj),
//        headers: headers
//    })
//        .catch(err => console.error(err.message))
//
//    const responseArr = await response.json()
//
//
//
//}

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
                title: title.value,
                artist: artist.value,
                genre: genre.value,
                year: year.value
    }
    await addMusic(bodyObj);

}

async function addMusic(obj) {
    const response = await fetch(`${baseUrl}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
    document.getElementById("title-input").value = "";
                document.getElementById("artist-input").value = "";
                document.getElementById("genre-input").value = "";
                document.getElementById("year-input").value = "";
        return getMusic();
    }

}

async function getMusic() {
    await fetch(`${baseUrl}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createMusicCards(data))
        .catch(err => console.error(err))
}

async function updateMusic(musicId){




document.querySelector('.editTable').setAttribute('style', 'display: block;')
    let bodyObj = {
        id: musicId,
        title: updateTitle.value,
        artist: updateArtist.value,
        genre: updateGenre.value,
        year: updateYear.value
    }

        const response = await fetch(`${baseUrl}`, {
            method: "PUT",
            body: JSON.stringify(bodyObj),
            headers: headers
        })
            .catch(err => console.error(err.message))
        if (response.status == 200) {
        document.getElementById("updateTitle").value = "";
        document.getElementById("updateArtist").value = "";
        document.getElementById("updateGenre").value = "";
        document.getElementById("updateYear").value = "";
        document.getElementById("editTable").style.display = "none";
                document.getElementById("editTableSubmit").style.display = "none";

            return getMusic();
        }

}

async function handleDelete(musicId){

    await fetch(baseUrl + "/" + musicId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getMusic();
}

//const createMusicCards = (array) => {
////    musicContainer.innerHTML = ''
//
//    var newRow=document.getElementById('table').insertRow();
//    array.forEach(obj => {
//    console.log(obj);
//
//    let musicCard = document.createElement("tr")
////        musicCard.classList.add("m-2")
//        newRow.innerHTML = `
//
//
//
//                                    <th scope="row">${obj.title}</th>
//                                    <td scope="row">${obj.artist}</td>
//                                    <td scope="row">${obj.genre}</td>
//                                    <td scope="row">${obj.year}</td>
//
//                                    <td><button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button></td>
//
//
//`
//
////        table.append(newRow);
//    })
//
//}

const createMusicCards = (array) => {
let tableData = ""


    array.forEach(obj => {

//     <button class="btn btn-info" onclick="updateMusic(${obj.id})">Update</button>
    tableData += `
     <tr>
     <th scope="row">${obj.title}</th>
     <td scope="row">${obj.artist}</td>
     <td scope="row">${obj.genre}</td>
     <td scope="row">${obj.year}</td>

     <td width="265rem">

    <button class="btn btn-info" onclick="editTableDisplay(${obj.id})">Update</button>
     <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
     <button class="btn btn-success" onclick="reviewPage(${obj.id})">Reviews</button>
     </td>
      </tr>

    `



    document.getElementById("table_body").innerHTML = tableData
editTableSubmit.innerHTML = `

                <button onclick="updateMusic(${obj.id})" class="editRowBtn">Update</button><br><br>

                `




//        table.append(newRow);
    })


}

function reviewPage(id){
document.cookie = `musicId=${id}`

//window.location.replace("http://localhost:8080/review.html");
window.location.replace("http://localhost:63342/java-proj-4%203/noteApp/static/review.html");


}

function logout(){
window.location.replace("http://localhost:63342/java-proj-4%203/noteApp/static/login.html")

let cookies = document.cookie.split(";");
for (let i in cookies) {
        document.cookie = /^[^=]+/.exec(cookies[i])[0]+"=; expires = Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

function editTableDisplay(id){
    document.querySelector('.editTable').setAttribute('style', 'display: block;')
    document.querySelector('.editTableSubmit').setAttribute('style', 'display: block;')




    return id
}

getMusic();
form.addEventListener("submit", handleSubmit)