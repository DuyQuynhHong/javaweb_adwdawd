
document.querySelectorAll('.hotel-dropdown .dropdown-content a').forEach(function (item) {
item.addEventListener('click', function (event) {
    const dropdownBtn = document.querySelector('.dropdown-btn');
    dropdownBtn.textContent = this.textContent+" >";
    window.location.href = this.href;
    const dropdown = document.querySelector('.dropdown-content');
    dropdown.style.display = "none";
});
});

document.querySelector('.dropdown-btn').addEventListener('click', function () {
const dropdown = document.querySelector('.dropdown-content');
if (dropdown.style.display === "none" || !dropdown.style.display) {
dropdown.style.display = "block";
} else {
dropdown.style.display = "none";
}
});

document.addEventListener('click', function (event) {
const dropdown = document.querySelector('.dropdown-content');
const dropdownBtn = document.querySelector('.dropdown-btn');
if (!dropdown.contains(event.target) && !dropdownBtn.contains(event.target)) {
dropdown.style.display = "none";
}
});