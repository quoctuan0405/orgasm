const selectElements = document.querySelectorAll(".select-role");

for (selectElement of selectElements) {
    selectElement.addEventListener("change", (event) => {
        console.log(event.target.parentElement);
        event.target.parentElement.submit();
    });
}