//vyskakovací okno pro editaci lodí/zaměstnanců
function openForm() {
    let form = document.getElementById("myForm")
    form.style.display = "block";

    return form
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

//vyskakovací okno pro přihlášení
function openForm2() {
    document.getElementById("myForm2").style.display = "block";
}

function closeForm2() {
    document.getElementById("myForm2").style.display = "none";
}
