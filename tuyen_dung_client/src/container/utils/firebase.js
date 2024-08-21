// Import the functions you need from the SDKs you need

import firebase from "firebase/compat/app";
import "firebase/compat/auth";
const firebaseConfig = {
  apiKey: "AIzaSyBewV5xgPKB0a-_eNiuKq5zyJJn-eHr4J0",
  authDomain: "findjobproject-37fda.firebaseapp.com",
  projectId: "findjobproject-37fda",
  storageBucket: "findjobproject-37fda.appspot.com",
  messagingSenderId: "725458495025",
  appId: "1:725458495025:web:135f45e3fbc79a44735710",
  measurementId: "G-Y6X676G46M"
};

// Initialize Firebase

firebase.initializeApp(firebaseConfig);
export default firebase;