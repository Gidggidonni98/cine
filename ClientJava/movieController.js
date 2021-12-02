// GET ID MOVIE

const getMovieById = async (id) => {
  return await $.ajax({
    type: "GET",
    url: "http://localhost:8080/cinema_war_exploded/movie/" + id,
  }).done((res) => res);
};

// GET ID TO DELETE

const getId = async (id) => {
  document.getElementById("id_delete").value = id;
};

//DETAILS

const getDetails = async (id) => {
  return await $.ajax({
    type: "GET",
    url: "http://localhost:8080/cinema_war_exploded/movie/" + id,
  }).done((res) => {
    let movie = res;
    var f = new Date(movie.dateCreated).toLocaleString();
    if (movie.dateUpdate == null) {
      var h = "No ha habido actualización";
    } else {
      var h = new Date(movie.dateUpdate).toLocaleString();
    }
    document.getElementById("title").value = movie.title;
    document.getElementById("description").value = movie.description;
    document.getElementById("synopsis").value = movie.synopsis;
    document.getElementById("rating").value = movie.rating;
    document.getElementById("dateCreated").value = f;
    document.getElementById("dateUpdate").value = h;
    document.getElementById("state").value = movie.state
      ? "Activo"
      : "Inactivo";
    document.getElementById("category").value = movie.category;
  });
};

//GET UDPATE DETAILS

const getInfoUpdate = async (id) => {
  return await $.ajax({
    type: "GET",
    url: "http://localhost:8080/cinema_war_exploded/movie/" + id,
  }).done((res) => {
    let movie = res;
    var f = new Date(movie.dateCreated).toLocaleString();
    if (movie.dateUpdate == null) {
      var h = "No ha habido actualización";
    } else {
      var h = new Date(movie.dateUpdate).toLocaleString();
    }

    document.getElementById("id_update").value = id;
    document.getElementById("title_update").value = movie.title;
    document.getElementById("description_update").value = movie.description;
    document.getElementById("synopsis_update").value = movie.synopsis;
    document.getElementById("rating_update").value = movie.rating;
    document.getElementById("dateCreated_update").value = f;
    document.getElementById("dateUpdate_update").value = h;
    document.getElementById("state_update").value = movie.state;
    document.getElementById("category_update").value = movie.category;
  });
};

//FILL TABLE MOVIES

const getMovies = () => {
  $.ajax({
    type: "GET",
    headers: { Accept: "application/json" },
    url: "http://localhost:8080/cinema_war_exploded/movie",
  }).done((res) => {
    let listMovie = res;

    let table = "";

    if (listMovie.length > 0) {
      for (let i = 0; i < listMovie.length; i++) {
        var f = new Date(listMovie[i].dateCreated).toLocaleString();
        if (listMovie[i].dateUpdate == null) {
          var h = "No ha habido actualización";
        } else {
          var h = new Date(listMovie[i].dateUpdate).toLocaleString();
        }

        table += `
            <tr>
                <td>${i + 1}</td>
                <td>${listMovie[i].title}</td>
                <td>${listMovie[i].description}</td>
                <td>${listMovie[i].synopsis}</td>
                <td>${listMovie[i].rating}</td>
                <td>${f}</td>
                <td>${h}</td>
                <td>${listMovie[i].state ? "Activo" : "Inactivo"}</td>
                <td>${listMovie[i].category}</td>
                <td style="text-align: center;">
                    <button type="button" onclick= getDetails(${
                      listMovie[i].id
                    }) class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#details"><i class="far fa-file-alt"></i></button>
                </td>
                <td style="text-align: center;">
                <button type="button" onclick= getInfoUpdate(${
                  listMovie[i].id
                }) class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#update"><i class="fa fa-edit"></i></button>
                </td>
                <td style="text-align: center;">
                <button type="button" onclick= getId(${
                  listMovie[i].id
                }) class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#delete"><i class="fas fa-trash-alt"></i></button>                
                </td>
            </tr>
            `;
      }
    } else {
      table = `
        <tr class="text-center">
            <td colspan="5">No hay registros para mostrar</td>
        </tr>
        `;
    }
    $(`#table > tbody`).html(table);
  });
};

//UPDATE MOVIE

const updateMovie = async () => {
    let movie = new Object();
  let id = document.getElementById("id_update").value;
  movie.title = document.getElementById("title_update").value;
  movie.description = document.getElementById("description_update").value;
  movie.synopsis = document.getElementById("synopsis_update").value;
  movie.dateUpdate = new Date().toLocaleString();
  movie.rating = document.getElementById("rating_update").value;
  movie.state = document.getElementById("state_update").value;
  movie.category = document.getElementById("category_update").value;

  $.ajax({
    type: "POST",
    url: "http://localhost:8080/cinema_war_exploded/movie/save/" + id,
    data: movie
  }).done(res => {
      console.log(movie);
    getMovies();
  });
};

// DELETE MOVIE

const deleteMovie = async () => {
  let id = document.getElementById("id_delete").value;
  await $.ajax({
    type: "POST",
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/x-www-form-urlencoded"
    },
    url: "http://localhost:8080/cinema_war_exploded/movie/delete/" + id,
  }).done((res) => {
      console.log(res);
    getMovies();
  });
};

//REGISTER MOVIE

const registerMovie = async() => {
  let movie = new Object();  
  movie.title = document.getElementById("title_register").value;
  movie.description = document.getElementById("description_register").value;
  movie.synopsis = document.getElementById("synopsis_register").value;
  movie.rating = document.getElementById("rating_register").value;
  movie.dateCreated = new Date();
  movie.state = 1;
  movie.category = document.getElementById("category_register").value;

await $.ajax({
    type: "POST",
    headers: {
        "Accept": "application/json",
        "Content-Type": "application/x-www-form-urlencoded"
    },
    url: "http://localhost:8080/cinema_war_exploded/movie/save/",
    data: movie
  }).done(res => {
    getMovies();
    document.getElementById("title_register").value = "";
    document.getElementById("description_register").value = "";
    document.getElementById("synopsis_register").value = "";
    document.getElementById("rating_register").value = "";
    document.getElementById("category_register").value = "";
  });
};

getMovies();
