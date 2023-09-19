const submitForm = document.getElementById("review-form")
const reviewContainer = document.getElementById("review-container")

const review = document.getElementById("review-input");

const cookieArr = document.cookie.split(/[&;=]/g);

let musicId = cookieArr[3];
let userId = cookieArr[1];
console.log(userId);
console.log(musicId);
console.log(cookieArr)


const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/reviews/"

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        review: review.value,
        userId: userId,
        musicId: musicId
    }
    await addReview(bodyObj);
    document.getElementById("review-input").value = ''
}

async function addReview(obj) {
    const response = await fetch(`${baseUrl}${userId}/music/${musicId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getReviews(musicId);
    }
}

async function getReviews(musicId) {
    await fetch(`${baseUrl}music/${musicId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createReviewCards(data))
        .catch(err => console.error(err))
}


const createReviewCards = (array) => {

    reviewContainer.innerHTML = ''
    array.forEach(obj => {
    console.log(obj.userDto.username)
        let reviewCard = document.createElement("div")
        reviewCard.classList.add("m-2")
//        reviewCard.innerHTML = `
//            <div class="card d-flex" style="width: 18rem; height: 18rem;">
//                <div class="card-body d-flex flex-column  justify-content-between" style="padding:1%; width:96%; border: 1px solid #100; margin-bottom:10px ">
//                    <p class="card-text">${obj.review}</p>
//                    <div class="d-flex justify-content-between">
//                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
//                        <button onclick="getNoteById(${obj.id})" type="button" class="btn btn-primary"
//                        data-bs-toggle="modal" data-bs-target="#note-edit-modal">
//                        Edit
//                        </button>
//                    </div>
//                </div>
//            </div>
//        `
        reviewCard.innerHTML = `

         <div class="card mb-4">
                  <div class="card-body">
                    <p>${obj.review}</p>

                    <div class="d-flex justify-content-between">
                      <div class="d-flex flex-row align-items-center">

                        <p class="small mb-0 ms-2">${obj.userDto.username}</p>
                      </div>
                      <div class="d-flex flex-row align-items-center">

                        <i class="far fa-thumbs-up mx-2 fa-xs text-black" style="margin-top: -0.16rem;"></i>

                      </div>
                    </div>
                  </div>
                </div>

        `
        reviewContainer.append(reviewCard);
    })
}
getReviews(musicId)
submitForm.addEventListener("submit", handleSubmit)