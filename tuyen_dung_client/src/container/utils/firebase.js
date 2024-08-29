import { initializeApp } from 'firebase/app';
import { getAuth } from 'firebase/auth';

const firebaseConfig = {
  apiKey: "AIzaSyBw_qc3zfWSZA3aOpUBS8dbGZAVapice_E",
  authDomain: "project-find-job.firebaseapp.com",
  projectId: "project-find-job",
  storageBucket: "project-find-job.appspot.com",
  messagingSenderId: "331878466028",
  appId: "1:331878466028:web:4eee63f69e00da60a700c9",
  measurementId: "G-G6RK5P2FCB"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

export { auth };
