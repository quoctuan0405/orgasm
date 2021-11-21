const selectElements = document.querySelectorAll(".select-role");

for (selectElement of selectElements) {
    selectElement.addEventListener("change", (event) => {
        console.log(event.target.parentElement);
        event.target.parentElement.submit();
    });
}

const datatable = new simpleDatatables.DataTable("#revenue-products-table", {
    fixedHeight: false
});
console.log(productsReport);

console.log(datatable);

let newData = {
    headings: productsReportHeader,
    data: productsReport
};

datatable.insert(newData);