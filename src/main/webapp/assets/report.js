import {userPreferredTheme} from "./index.js";
import "https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.min.js";

const reportContainer = document.querySelector("#report-container");

document.addEventListener('DOMContentLoaded', () => {
  if (reportContainer) {
    debugger;
    const button = document.getElementById("report-pdf-download");

    const theme = userPreferredTheme()

    const pdfContent = reportContainer.cloneNode(true);
    pdfContent.setAttribute("style", `padding:1em;background-color:${theme === "dark" ? "#1d232a" : "#ffffff"}`)

    button.addEventListener("click", () => {
      html2pdf(pdfContent);
    })
  }
})
  