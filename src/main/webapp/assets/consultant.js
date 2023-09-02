'use strict';
import z from 'https://cdn.jsdelivr.net/npm/zod@3.22.2/+esm'
import {countries, clearErrors, extractDataFromForm, validateForm} from './index.js';

window.addEventListener('DOMContentLoaded', () => {
  const countriesHtml = countries.map((country) => `<option value="${country}">${country}</option>`).join("")
  document.querySelector("#countries").innerHTML = countriesHtml;
})

// consultant form
const schema = z.object({
  email: z
    .string()
    .trim()
    .nonempty('Email is required.')
    .toLowerCase()
    .email('Invalid email address.'),
  phoneNumber: z.string().trim().nonempty('Phone number is required.'),
  firstName: z.string().trim().nonempty('First name is required.'),
  lastName: z.string().trim().nonempty('Last name is required.'),
  country: z.string().trim().nonempty('Specialized country is required.'),
  job: z.string().trim().nonempty('Specialized job is required.'),
});

/** @type {HTMLFormElement}*/
const manageConsultantForm = document.querySelector('#consultantForm');
/** @type {HTMLFormElement}*/
const deleteConsultantForm = document.querySelector("#consultantDeleteForm");
manageConsultantForm.addEventListener('submit', validateForm(schema));
manageConsultantForm.addEventListener('reset', event =>
  clearErrors(event.target, extractDataFromForm(event.target))
);

/** @type{HTMLDialogElement} */
const manageConsultantFormDialog = document.getElementById('consultantDialog');
manageConsultantFormDialog.addEventListener('close', () => {
  manageConsultantForm.reset();

  manageConsultantForm.id.value = '';
  manageConsultantForm.firstName.value = '';
  manageConsultantForm.lastName.value = '';
  manageConsultantForm.email.value = '';
  manageConsultantForm.phoneNumber.value = '';
  manageConsultantForm.country.value = '';
  manageConsultantForm.job.value = '';
  manageConsultantForm.actionType.value = 'CREATE';

  consultantAvailabilityEntryContainer.innerHTML = '';

});

document.getElementById('create-consultant')?.addEventListener('click', () => {
  manageConsultantFormDialog.showModal();
});

document.querySelectorAll('[data-action="edit"]').forEach(edit => {
  edit.addEventListener('click', () => {
    manageConsultantFormDialog.showModal();
    const record = JSON.parse(edit.dataset.record.replaceAll("&quot;", '"'));


    manageConsultantForm.id.value = record.id;
    manageConsultantForm.firstName.value = record.firstName;
    manageConsultantForm.lastName.value = record.lastName;
    manageConsultantForm.email.value = record.email;
    manageConsultantForm.phoneNumber.value = record.phoneNumber;
    manageConsultantForm.country.value = record.country;
    manageConsultantForm.job.value = record.job;

    manageConsultantForm.actionType.value = 'UPDATE';

    record.availability.forEach((availability) => {
      const newEntry = document.importNode(consultantAvailabilityEntry.content, true);
      newEntry.querySelector("[name='id[]']").value = availability.id;
      newEntry.querySelector("[name='day[]']").value = availability.day;
      newEntry.querySelector("[name='startTime[]']").value = availability.startTime;
      newEntry.querySelector("[name='endTime[]']").value = availability.endTime;
      consultantAvailabilityEntryContainer.appendChild(newEntry);
    })

  });
});

document.querySelectorAll('[data-action="delete"]').forEach(button => {
  button.addEventListener('click', () => {
    confirmationDialog.showModal();
    const record = JSON.parse(button.dataset.record);

    deleteConsultantForm.id.value = record.id;
    deleteConsultantForm.querySelector('#displayId').textContent = record.id;
  });
});

const consultantAvailability = manageConsultantForm.querySelector("#consultant-availability");
const consultantAvailabilityAddEntry = consultantAvailability.querySelector("#add-entry")
const consultantAvailabilityEntryContainer = consultantAvailability.querySelector("#entry-container")
/** @type {HTMLTemplateElement} */
const consultantAvailabilityEntry = document.querySelector('#consultant-availability-entry');
consultantAvailabilityAddEntry.addEventListener('click', () => {
  const newEntry = document.importNode(consultantAvailabilityEntry.content, true);
  consultantAvailabilityEntryContainer.appendChild(newEntry);
})