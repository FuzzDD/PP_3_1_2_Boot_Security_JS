
console.log("asd")
/*fetch("http://localhost:8080/api/users")
    .then(response => response.json())
    .then(data => {
        console.log(data)
    })
.catch(error => console.log(error));*/
let url = "http://localhost:8080/";

try {
    let response = await fetch(url + "api/users");

    if (response.ok) {
        let json = await response.json();
        let tbody = document.getElementById("tableShowAllUsers");
        let tr = document.createElement("tr")
        let td = document.createElement("td")
        td.innerText="ID here"
        tr.append(td)
        tbody.append(tr)

    } else {
        alert("Ошибка HTTP: " + response.status);
    }
} catch (error) {
    console.error("Ошибка получения данных:", error);
}
