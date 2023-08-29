'use strict';
import z from 'https://cdn.jsdelivr.net/npm/zod@3.22.2/+esm'
import {
    clearErrors,
    extractDataFromForm,
    validateForm,
} from './index.js';


// consultant form
const schema = z.object({
    firstName: z.string().trim().nonempty('First name is required.'),
    lastName: z.string().trim().nonempty('Last name is required.'),
    email: z.string().trim().nonempty('Email is required.').email(),
    phoneNumber: z.string().trim().nonempty('Phone number is required.')
});

const manageJobSeekerForm = document.appointmentForm;
const deleteJobSeekerForm = document.appointmentDeleteForm;
manageJobSeekerForm.addEventListener('submit', validateForm(schema));
manageJobSeekerForm.addEventListener('reset', event =>
    clearErrors(event.target, extractDataFromForm(event.target))
);

const manageJobSeekerFormDialog = document.getElementById('appointmentDialog');
manageJobSeekerFormDialog.addEventListener('close', () => {
    manageJobSeekerForm.reset();

    manageJobSeekerForm.id.value = '';
    manageJobSeekerForm.firstName.value = '';
    manageJobSeekerForm.lastName.value = '';
    manageJobSeekerForm.email.value = '';
    manageJobSeekerForm.phoneNumber.value = '';
    manageJobSeekerForm.actionType.value = 'CREATE';

});

document.getElementById('create-job-seeker')?.addEventListener('click', () => {
    manageJobSeekerFormDialog.showModal();
});

document.querySelectorAll('[data-action="edit"]').forEach(edit => {
    edit.addEventListener('click', () => {
        manageJobSeekerFormDialog.showModal();
        const record = JSON.parse(edit.dataset.record.replaceAll("&quot;", '"'));

        manageJobSeekerForm.id.value = record.id;
        manageJobSeekerForm.firstName.value = record.firstName;
        manageJobSeekerForm.lastName.value = record.lastName;
        manageJobSeekerForm.email.value = record.email;
        manageJobSeekerForm.phoneNumber.value = record.phoneNumber;

        manageJobSeekerForm.actionType.value = 'UPDATE';

    });
});

document.querySelectorAll('[data-action="delete"]').forEach(button => {
    button.addEventListener('click', () => {
        confirmationDialog.showModal();
        const record = JSON.parse(button.dataset.record);

        deleteJobSeekerForm.id.value = record.id;
        deleteJobSeekerForm.querySelector('#displayId').textContent = record.id;
    });
});
