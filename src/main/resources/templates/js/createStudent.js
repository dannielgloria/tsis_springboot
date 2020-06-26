const form = document.querySelector("#new-student form");

function sendHttpRequest(method, url, data) {
    return fetch(url, {
            method: method,
            body: data,
        })
        .then((response) => {
            if (response.status >= 200 && response.status < 300) {
                return response.json();
            } else {
                return response.json().then((errData) => {
                    console.log(errData);
                    throw new Error("Something went wrong - server-side.");
                });
            }
        })
        .catch((error) => {
            console.log(error);
            throw new Error("Something went wrong!");
        });
}


async function createPost(name, registryNumber, divicion, carrer) {
    const student = {
        name: name,
        registryNumber: registryNumber,
        divicion: divicion,
        carrer: carrer,
    };

    const fd = new FormData(form);
    fd.append("student", student);

    const response = await axios.post(
        "http://localhost:8080/students",
        student
    );
    console.log(response);
}

form.addEventListener("submit", (event) => {
    event.preventDefault();
    const registryName = event.currentTarget.querySelector("#name").value;
    const registryRegistryNumber = event.currentTarget.querySelector("#registryNumber")
        .value;
    const registryDivicion = event.currentTarget.querySelector("#divicion").value;
    const registryCarrer = event.currentTarget.querySelector("#carrer")
        .value;

    createPost(registryName, registryRegistryNumber, registryDivicion, registryCarrer);
});