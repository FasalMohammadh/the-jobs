'use strict';

import z from 'https://cdn.jsdelivr.net/npm/zod@3.22.2/+esm'
import {
  clearErrors,
  extractDataFromForm,
  validateForm,
} from './index.js';


const schema = z
  .object({
    firstName: z.string().trim().nonempty('First Name is required.'),
    lastName: z.string().trim().nonempty('Last Name is required.'),
    phoneNumber: z.string().trim().nonempty('Phone number is required.'),
    email: z.string().trim().nonempty('Email is required.').email(),
    password: z.string().trim().nonempty('Password is required.'),
    confirmPassword: z.string().trim().nonempty('Confirm password is required.'),
  })
  .superRefine((schema, ctx) => {
    if (schema.password && schema.confirmPassword) {
      if (schema.password.trim() === schema.confirmPassword.trim()) return schema;

      ctx.addIssue({
        code: 'custom',
        path: ['confirmPassword'],
        message: 'Passwords do not match.',
      });
      return z.NEVER;
    }
  });

const registerForm = document.querySelector("#register-form");
registerForm.addEventListener('submit', validateForm(schema));
registerForm.addEventListener('reset', event =>
  clearErrors(event.target, extractDataFromForm(event.target))
);