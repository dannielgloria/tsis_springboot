const listElement = document.querySelector(".alumnos");
const postTemplate = document.getElementById("single-alumno");
const fetchButton = document.querySelector("#available-alumnos button");
const postList = document.querySelector("ul");

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

async function fetchPosts() {
    try {
        const response = await axios.get("http://localhost:8080/alumnos");
        const listOfPosts = response.data;
        listElement.innerHTML = "";
        for (const post of listOfPosts) {
            console.log(post);

            const postEl = document.importNode(postTemplate.content, true);
            postEl.querySelector("h2").textContent = post.matricula;
            postEl.querySelector("h3").textContent = post.nombre;
            postEl.querySelector("p").textContent = post.carrera;
            postEl.querySelector("li").id = post.matricula;
            listElement.append(postEl);
        }
    } catch (error) {
        alert(error.message);
        console.log(error.response);
    }
}

async function patchAlumno(matricula, nombre, carrera) {
    const alumno = {
        matricula: matricula,
        nombre: nombre,
        carrera: carrera,
    };

    const fd = new FormData(form);
    fd.append("alumno", alumno);

    const response = await axios.patch(
        `http://localhost:8080/alumnos/${matricula}`,
        alumno
    );
    console.log(response);
    fetchPosts();
}

fetchButton.addEventListener("click", fetchPosts);


postList.addEventListener("click", (event) => {
    if (event.target.tagName === "BUTTON") {
        if (event.target.id === "btn-delete") {
            const li = event.target.closest("li");
            axios.delete(`http://localhost:8080/alumnos/${li.id}`);
            li.remove();
        }
        if (event.target.id === "btn-update") {
            const li = event.target.closest("li");
            const matricula = li.querySelector("h2").innerHTML;
            const nombre = li.querySelector("h3").innerHTML;
            const carrera = li.querySelector("p").innerHTML;
            patchAlumno(matricula, nombre, carrera);
        }
    }
});

fetchPosts();