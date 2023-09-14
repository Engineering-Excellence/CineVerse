var apiKey = "06b1c66d3baf07cdfabaf28b3876e74a";
$(document).ready(() => {
    $.ajax({
        url: "/movie/loved/" + username,
        type: "get",
        async: true,
        success: (res) => {
            console.log(res)
            let html = "";
            for (let i = 0; i < Math.min(3, res.length); i++) {
                let curr = res[i];
                $.ajax({
                    url: `https://api.themoviedb.org/3/movie/${curr}?api_key=${apiKey}&language=ko-KR&region=KR`,
                    type: "get",
                    async: false,
                    success: (data) => {
                        html += `<a href="/movie/view?id=${data["id"]}">`
                        html += `<img class="loved-img" src="http://image.tmdb.org/t/p/w342${data["poster_path"]}">`
                        html += `</a>`
                    },
                });
            }
            $(".img-div").append(html);
        },
    });
});