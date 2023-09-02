'use strict';
import z from 'https://cdn.jsdelivr.net/npm/zod@3.22.2/+esm'
import {
  clearErrors,
  extractDataFromForm,
  validateForm,
} from './index.js';


// consultant form
const schema = z.object({
  email: z.string().trim().nonempty('Email is required.').email(),
  password: z.string().trim().nonempty('Password is required.')
});

const loginForm = document.querySelector("#loginForm");
loginForm.addEventListener('submit', validateForm(schema));
loginForm.addEventListener('reset', event =>
  clearErrors(event.target, extractDataFromForm(event.target))
);