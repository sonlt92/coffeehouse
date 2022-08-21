let searchForm = document.querySelector('.search-form');

document.querySelector('#search-btn').onclick = () =>{
  searchForm.classList.toggle('active');

};
function hamDropdown() {
  document.querySelector(".noidung_dropdown").classList.toggle("hienThi");
};

function noiDropdown() {
  document.querySelector(".noi_dropdown").classList.toggle("inRa");
}


