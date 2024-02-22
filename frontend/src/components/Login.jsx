import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const Notify = (message) => {
    toast(message, {
      position: toast.POSITION.TOP_CENTER,
    });
  };

  async function login(event) {
    event.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/statistics/security/login",
        {
          login: email,
          password: password,
        }
      );

      const data = response.data;

      if (data.message === "Email doesn't exist") {
        Notify("Email doesn't exist. Please try again.");
      } else if (data.message === "Login Success") {
        navigate("/home");
      } else {
        Notify("Wrong password. Please try again.");
      }
    } catch (err) {
      console.error(err);
      alert("An error occurred during login.");
    }
  }

  return (

    <div>
      <form className="login-form" onSubmit={login}>
        <h1>Vodahory statistics</h1>
        <h2>Login</h2>
        <label htmlFor="email">Email</label>
        <input
          type="email"
          placeholder="admin@gmail.com"
          id="email"
          value={email}
          onChange={(event) => setEmail(event.target.value)}
        />
        <label htmlFor="password">Password</label>
        <input
          type="password"
          className="form-control"
          placeholder="*********"
          id="password"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
        <button className="login-button" type="submit">
          Log In
        </button>
      </form>
      <ToastContainer />
    </div>
  );
}

export default Login;
