const nav = document.querySelector(".primary-navigation");
const navToggle = document.querySelector(".mobile-nav-toggle");

navToggle.addEventListener("click", () => {

    const visiblity = nav.getAttribute("data-visible");
    if (visiblity === "false") {
        nav.setAttribute("data-visible", true);
        navToggle.setAttribute("aria-expanded", true);
    } else {
        nav.setAttribute("data-visible", false);
        navToggle.setAttribute("aria-expanded", false);
    }
})

function showUserPopup() {
    fetch('/user-info')
        .then(response => response.json())
        .then(data => {
            // Update the contents of the popup window with the user data
            document.querySelector('#user-popup.username').textContent = data.username;
            // ...
            // Show the popup window
            document.querySelector('#user-popup').classList.add('visible');
        });
}


