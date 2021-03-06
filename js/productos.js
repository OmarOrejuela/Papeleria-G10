const url = "http://localhost:8080/APIGR10/api/productos";
const contenedor = document.querySelector('tbody')
let resultados = ''


const modalProductos = new bootstrap.Modal(document.getElementById('modalProducto'))
const formProductos = document.querySelector('form')
const descripcionProducto = document.getElementById('descripcion')
const precioProducto = document.getElementById('precio')
const idProducto = document.getElementById('id_Producto')

let opcion = ''

btnCrear.addEventListener('click', () => {
    descripcionProducto.value = ''
    precioProducto.value = ''
    idProducto.value = ''
    idProducto.disabled = false
    modalProductos.show()
    opcion = 'crear'
})


const ajax = (options) => {
    let { url, method, success, error, data } = options;
    const xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", (e) => {
        if (xhr.readyState !== 4) return;

        if (xhr.status >= 200 && xhr.status < 300) {
            let json = JSON.parse(xhr.responseText);
            success(json);
        } else {
            let message = xhr.statusText || "Ocurrió un error";
            error(`Error ${xhr.status}: ${message}`);
        }
    });

    xhr.open(method || "GET", url);
    xhr.setRequestHeader("Content-type", "application/json; charset=utf-8");
    xhr.send(JSON.stringify(data));
};



const getAll = () => {
    ajax({
        url: url,
        success: (res) => {
            console.log(res);

            res.forEach((Productos) => {
                resultados += `<tr>
                        <td width="10%">${Productos.id_producto}</td>
                        <td width="15%">${Productos.descripcion}</td>
                        <td width="15%">${Productos.precio}</td>
                        <td class="text-center" width="20%"><a class="btnEditar btn btn-primary">Editar</a><a class="btnBorrar btn btn-danger">Borrar</a></td>
                    </tr>`
            });

            contenedor.innerHTML = resultados
        },
        error: (err) => {
            console.log(err);
            $table.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`);
        },
    });
};
document.addEventListener("DOMContentLoaded", getAll);
document.addEventListener("click", (e) => {

    if (e.target.matches(".btnBorrar")) {
        const fila = e.target.parentNode.parentNode
        const id = fila.firstElementChild.innerHTML
        console.log(id)
        alertify.confirm(`¿Estás seguro de eliminar el id ${id}?`,
            function () {
                ajax({
                    url: url + "/" + id,
                    method: "DELETE",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    success: (res) => location.reload(),
                    error: (err) => alert(err),
                });
                alertify.success('Registro eliminado')
            },
            function () {
                alertify.error('Cancel')
            });



    }
    if (e.target.matches(".btnEditar")) {
        const fila = e.target.parentNode.parentNode
        idProducto.value = fila.children[0].innerHTML
        descripcionProducto.value = fila.children[1].innerHTML
        precioProducto.value = fila.children[2].innerHTML
        idProducto.disabled = true
        opcion = 'editar'
        modalProductos.show()
    }
})

formProductos.addEventListener('submit', (e) => {
    e.preventDefault()
    let metodo = "POST"
    if (opcion == 'editar') {
        metodo = "PUT"

    }
    ajax({
        url: url,
        method: metodo,
        headers: {
            'Content-Type': 'application/json'
        },
        success: (res) => location.reload(),
        error: (err) =>
            $form.insertAdjacentHTML("afterend", `<p><b>${err}</b></p>`),
        data: {
            "id_Producto": idProducto.value,
            "descripcion": descripcionProducto.value,
            "precio": precioProducto.value
        },
    });
    modalProductos.hide()
})
