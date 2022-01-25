//vyskakovací okno pro editaci lodí/zaměstnanců
function openForm() {
    let form = document.getElementById("myForm")
    form.style.display = "block";

    return form
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

//vyskakovací okno pro přidání nákladu (po kliknutí n tlačítko "Přidat nový náklad")
function openForm3() {
    document.getElementById("myForm3").style.display = "block";
}

function closeForm3() {
    document.getElementById("myForm3").style.display = "none";
}

//vyskakovací okno pro editaci nákladu (po kliknutí n tlačítko "Upravit")
function openForm4() {
    document.getElementById("myForm4").style.display = "block";
}

function closeForm4() {
    document.getElementById("myForm4").style.display = "none";
}