(ns scicloj.clay.v2.tool.scittle.styles)

(def table "
table {
  border-style: thin;
}
th, td {
  padding: 6px;
}
td {
  text-align: left;
}
th {
  text-align: center;
  background-color: #ddd;
}
tr:nth-child(even) {
  background-color: #f6f6f6;
}
")


;; https://afeld.github.io/bootstrap-toc/#customization
(def boostrap-toc "
nav[data-toggle=\"toc\"] {
  top: 42px;
}

nav[data-toggle=toc] .nav-link+ul {
 display:block;
 padding-bottom:10px;
}


/* small screens */
@media (max-width: 768px) {
  /* override stickyness so that the navigation does not follow scrolling */
  nav[data-toggle=\"toc\"] {
    margin-bottom: 42px;
    position: static;
  }

  /* PICK ONE */
  /* don't expand nested items, which pushes down the rest of the page when navigating */
  nav[data-toggle=\"toc\"] .nav .active .nav {
    display: none;
  }
  /* alternatively, if you *do* want the second-level navigation to be shown (as seen on this page on mobile), use this */
/*
nav[data-toggle='toc'] .nav .nav {
    display: block;
  }
*/
}
 ")

;; See https://www.w3schools.com/howto/howto_css_loader.asp
(def loader
  "
.loader {
  border: 16px solid #f3f3f3; /* Light grey */
  border-top: 16px solid #3498db; /* Blue */
  border-radius: 50%;
  width: 30px;
  height: 30px;
  animation: spin 2s linear infinite;
}

@keyframes spin {
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}
")
