import { useState } from "react";
import api from "../apis/api";
import { useNavigate } from "react-router-dom";

function Login() {

  const navigate = useNavigate();

  const [form, setForm] = useState({
    id: "",
    password: ""
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const login = async () => {

    try {

      const res = await api.post("/auth/login", form);

      const user = res.data;

      localStorage.setItem("user", JSON.stringify(user));

      navigate(`/user/${user.id}`);

    } catch (e) {

      alert("로그인 실패");

    }

  };

  return (
    <div>

      <h1>로그인</h1>

      <input name="id" placeholder="아이디" onChange={handleChange} />
      <br />

      <input name="password" type="password" placeholder="비밀번호" onChange={handleChange} />
      <br />

      <button onClick={login}>로그인</button>

    </div>
  );
}

export default Login;
