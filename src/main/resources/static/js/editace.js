
import {getData} from "./lib/request.js";

const loadLoadButtons = document.querySelectorAll('.loadLoadButton')

loadLoadButtons.forEach(function(currentBtn){
    currentBtn.addEventListener('click', getCargos)
})

 function getCargos(e) {
    e.preventDefault()
    let shipId = e.target.dataset.ship_id
    let shipName = e.target.dataset.ship_name
    getData('http://localhost:8080/cargos/' + shipId , {})
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
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
        button.setAttribute("onclick", "openForm4()")
        button.textContent = "Upravit"
        row.insertCell(5).appendChild(button)
    })

    tableNode.appendChild(table)
    tableNode.hidden = false
}







