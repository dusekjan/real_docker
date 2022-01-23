import {getData, putData, postData} from "./lib/request.js";


const editWorkerButtons = document.querySelectorAll('.editWorker')

editWorkerButtons.forEach(function(currentBtn){
    currentBtn.addEventListener('click', editWorker)
})

document.getElementById("postWorker").addEventListener("click", postWorker)


function postWorker(){

    let form = document.getElementById("newWorkerForm")

    const worker = {
        "firstName": form.querySelector("#firstName").value,
        "sureName": form.querySelector("#sureName").value,
        "role": form.querySelector("#role").value,
        "email": form.querySelector("#emailNew").value,
        "password": form.querySelector("#password").value,
        "phone": form.querySelector("#phone").value
    }

    postData("http://localhost:8080/worker", worker)
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);

                    if(response.status === 400 || response.status === 500){
                        alert("Nastal problém při zápisu do databáze, \n " +
                            "\"Poruseni integrity dat - neunikatni nebo nulova povinna data\"\n" + `[${response.status}]`)

                    } else {
                        alert("Problém při zápisu")
                    }

                    return;
                }

            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });
}


function editWorker(e) {
    e.preventDefault()
    let workerId = e.target.dataset.worker_id

    getData("http://localhost:8080/worker/" + workerId, {})
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }

                // Examine the text in the response
                response.json().then(function(data) {
                    fillWorkerForm(data, workerId)
                });
            }
        )
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
    })
}

function fillWorkerForm(data, workerId) {
    let form = document.getElementById("myForm")

    form.querySelector("#jmeno").value = data.firstName
    form.querySelector("#prijmeni").value = data.sureName
    form.querySelector("#naklad").value = data.role
    form.querySelector("#email").value = data.email
    form.querySelector("#telefon").value = data.phone

    let updateButton = form.querySelector("button#updateWorker");
    updateButton.setAttribute("data-worker_id", workerId)
    updateButton.addEventListener("click", updateWorker)

    form.style.display = "block"
}

function updateWorker(e){
    e.preventDefault()
    let workerId = e.target.dataset.worker_id

    let form = document.getElementById("myForm")

    const worker = {
        "id": workerId,
        "firstName": form.querySelector("#jmeno").value,
        "sureName": form.querySelector("#prijmeni").value,
        "role": form.querySelector("#naklad").value,
        "email": form.querySelector("#email").value,
        "phone": form.querySelector("#telefon").value
    }

    putData("http://localhost:8080/worker/" + workerId, worker)
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' + response.status);

                    alert("Nastal problém při zápisu do databáze, \n " +
                        "Ujistěte se, že zadaný email neni jiz pouzivan.\n" + `[${response.status}]`)

                    return;
                }
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });
}

