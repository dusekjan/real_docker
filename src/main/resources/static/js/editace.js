
import {deleteData, getData, postData, putData} from "./lib/request.js";
import {checkResponse} from "./lib/request.js";

const loadLoadButtons = document.querySelectorAll('.loadLoadButton')

loadLoadButtons.forEach(function(currentBtn){
    currentBtn.addEventListener('click', getCargos)
})

const editShipButtons = document.querySelectorAll('.editShipButton')

editShipButtons.forEach(function(currentBtn){
    currentBtn.addEventListener('click', getSchedule)
})

document.getElementById("addNewShipAndSchedule").addEventListener("click", postShipAndSchedule)

function getSchedule(e){
    e.preventDefault()
    let scheduleId = e.target.dataset.schedule_id
    let shipId = e.target.dataset.ship_id

    getData('http://localhost:8080/schedule/' + scheduleId)
        .then(
            function(response) {
                if (response.status !== 200) {
                    alert("problem při čtení databáze: [" + response.status + "]")
                    return;
                }

                // Examine the text in the response
                response.json().then(function(data) {
                    fillEditScheduleForm(data, scheduleId, shipId)
                });
            }
        )
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
        })
}

function fillEditScheduleForm(data, scheduleId, shipId) {
    let form = document.getElementById("myForm")

    form.querySelector("#nazevL").value = data.ship.name
    form.querySelector("#nazevS").value = data.ship.company
    form.querySelector("#naklad").value = data.ship.cargoType
    form.querySelector("#stav").value = data.state
    form.querySelector("#casP").value = data.arrivalTime
    form.querySelector("#casO").value = data.departureTime

    let updateButton = form.querySelector("button#updateSchedule");
    updateButton.setAttribute("data-schedule_id", scheduleId)
    updateButton.setAttribute("data-ship_id", shipId)
    updateButton.addEventListener("click", updateScheduleAndBoat)

    let deleteButton = form.querySelector("button#deleteSchedule");
    deleteButton.setAttribute("data-schedule_id", scheduleId)
    deleteButton.addEventListener("click", deleteScheduleAndBoatWithCargos)

    form.style.display = "block"
}

function updateScheduleAndBoat(e){
    e.preventDefault()
    let scheduleId = e.target.dataset.schedule_id
    let shipId = e.target.dataset.ship_id

    let form = document.getElementById("myForm")

    const ship = {
        "id": shipId,
        "name": form.querySelector("#nazevL").value,
        "company": form.querySelector("#nazevS").value,
        "cargoType": form.querySelector("#naklad").value,
    }

    const schedule = {
        "id": scheduleId,
        "arrivalTime": form.querySelector("#casP").value,
        "departureTime": form.querySelector("#casO").value,
        "state": form.querySelector("#stav").value,
    }

    putData("http://localhost:8080/ship/" + shipId, ship)
        .then(
            function(response) {
                checkResponse(response.status, "Problem při zapisu lodě")
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });

    putData("http://localhost:8080/schedule/" + scheduleId, schedule)
        .then(
            function(response) {
                checkResponse(response.status, "Problém při zápisu rozvrhu")
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });
}

function deleteScheduleAndBoatWithCargos(e){
    let scheduleId = e.target.dataset.schedule_id

    deleteData(`http://localhost:8080/scheduleAndShipWithCargos/${scheduleId}`)
        .then(
            function(response) {
                checkResponse(response.status)
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
        })
}


 function getCargos(e) {
    e.preventDefault()
    let shipId = e.target.dataset.ship_id
    let shipName = e.target.dataset.ship_name
    getData('http://localhost:8080/cargos/' + shipId)
        .then(
            function(response) {
                if (response.status !== 200) {
                    alert("problem při čtení databáze: [" + response.status + "]")
                    return;
                }

                // Examine the text in the response
                response.json().then(function(data) {
                    fillCargosTable(data, shipName, shipId)
                });
            }
        )
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
    })
}

function fillCargosTable(data, shipName, shipId){
    // clear content first
    console.log(data)
    let tableNode = document.getElementById("cargoNode")
    while (tableNode.firstChild) {
        tableNode.removeChild(tableNode.firstChild);
    }

    // header texts
    let header3 = document.createElement("H3")
    header3.classList.add("velke")
    header3.textContent = `Náklad lodi s názvem '${shipName}' a ID = ${shipId}:`
    tableNode.appendChild(header3)

    // create table
    let table = document.createElement("table");
    table.classList.add("table2")

    let head = ["Náklad", "Hodnota nákladu (Kč)", "Hmotnost nákladu (kg)", "Odesílatel", "Poznámka", "Upravit"]

    let headRow = table.insertRow()
    head.forEach((header) => {
        let headCell = document.createElement("TH")
        headCell.innerHTML = header
        headRow.appendChild(headCell)
    })

    data.forEach((cargo) => {
        let row = table.insertRow()
        row.insertCell(0).appendChild(document.createTextNode(cargo.name))
        row.insertCell(1).appendChild(document.createTextNode(cargo.price))
        row.insertCell(2).appendChild(document.createTextNode(cargo.weight))
        row.insertCell(3).appendChild(document.createTextNode(cargo.sender))
        row.insertCell(4).appendChild(document.createTextNode(cargo.note))

        let button = document.createElement("BUTTON")
        button.classList.add("uprostred2")
        button.classList.add("myButton1")
        button.setAttribute("data-cargo_id", cargo.id)
        button.textContent = "Upravit"
        button.addEventListener("click", getCargo)
        row.insertCell(5).appendChild(button)
    })

    let addNewCargoButton = document.getElementById("addNewCargo")
    addNewCargoButton.style.display = "inline-block"

    let postCargoButton = document.getElementById("postCargo")
    postCargoButton.setAttribute("data-ship_id", shipId)
    postCargoButton.addEventListener("click", postCargo)

    tableNode.appendChild(table)
    tableNode.hidden = false
}

function getCargo(e){
    e.preventDefault()

    let cargoId = e.target.dataset.cargo_id

    getData('http://localhost:8080/cargo/' + cargoId)
        .then(
            function(response) {
                if (response.status !== 200) {
                    alert("problem při čtení databáze: [" + response.status + "]")
                    return;
                }

                // Examine the text in the response
                response.json().then(function(data) {
                    fillEditCargoForm(data, cargoId)
                });
            }
        )
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
        })
}

function fillEditCargoForm(data, cargoId) {
    let form = document.getElementById("myForm4")

    form.querySelector("#nazevUpravit").value = data.name
    form.querySelector("#hodnotaUpravit").value = data.price
    form.querySelector("#hmotnostUpravit").value = data.weight
    form.querySelector("#odesUpravit").value = data.sender
    form.querySelector("#poznUpravit").value = data.note

    let updateButton = form.querySelector("button#updateCargo");
    updateButton.setAttribute("data-cargo_id", cargoId)
    updateButton.addEventListener("click", updateCargo)

    let deleteButton = form.querySelector("button#deleteCargo");
    deleteButton.setAttribute("data-cargo_id", cargoId)
    deleteButton.addEventListener("click", deleteCargo)

    form.style.display = "block"
}

function updateCargo(e) {
    e.preventDefault()
    let cargoId = e.target.dataset.cargo_id

    let form = document.getElementById("myForm4")

    const cargo = {
        "id": cargoId,
        "name": form.querySelector("#nazevUpravit").value,
        "weight": form.querySelector("#hmotnostUpravit").value,
        "note": form.querySelector("#poznUpravit").value,
        "price": form.querySelector("#hodnotaUpravit").value,
        "sender": form.querySelector("#odesUpravit").value
    }

    putData("http://localhost:8080/cargo/" + cargoId, cargo)
        .then(
            function(response) {
                checkResponse(response.status)
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });
}

function deleteCargo(e){
    let cargoId = e.target.dataset.cargo_id

    deleteData("http://localhost:8080/cargo/" + cargoId)
        .then(
            function(response) {
                checkResponse(response.status)
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.log("ERROR: ")
            console.log(error)
        })
}

function postCargo(e){
    e.preventDefault()
    let form = document.getElementById("myForm3")
    let shipId = e.target.dataset.ship_id

    const cargo = {
        "name": form.querySelector("#nazevPridat").value,
        "weight": form.querySelector("#hmotnostPridat").value,
        "note": form.querySelector("#poznPridat").value,
        "price": form.querySelector("#hodnotaPridat").value,
        "sender": form.querySelector("#odesPridat").value
    }

    postData("http://localhost:8080/cargo/" + shipId, cargo)
        .then(
            function(response) {
                checkResponse(response.status)
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });
}

function postShipAndSchedule(){
    let form = document.getElementById("newShipAndScheduleForm")

    const data = {
        "name": form.querySelector("#shipName").value,
        "company": form.querySelector("#shipCompany").value,
        "cargoType": form.querySelector("#shipCargoType").value,
        "state": form.querySelector("#scheduleState").value,
        "arrivalTime": form.querySelector("#scheduleArrivalTime").value,
        "departureTime": form.querySelector("#scheduleDepartureTime").value
    }

    postData("http://localhost:8080/scheduleAndShip/", data)
        .then(
            function(response) {
                checkResponse(response.status)
                if (response.status !== 200) return;
            }
        )
        .then(() => location.reload())
        .catch((error) => {
            console.error('Error:', error);
        });
}






