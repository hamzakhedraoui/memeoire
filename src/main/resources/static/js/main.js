let text = document.getElementById('text')
let mount = document.getElementById('mount')
let road = document.getElementById('road')
let moon = document.getElementById('moon')
let btn = document.getElementById('btn')

window.addEventListener("scroll", function(){
    let value = window.scrollY
    moon.style.top = value * -1.2 + "px"
    btn.style.marginTop = value * 1.5 + "px"
    text.style.marginTop = value * 0.5 + "px"
    mount.style.top = value * 0.4 + "px"
})