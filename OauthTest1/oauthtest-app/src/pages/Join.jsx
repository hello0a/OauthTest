import { useState } from "react";
import api from "../apis/api";

function Join() {

  const [form, setForm] = useState({
    id: "",
    password: "",
    nickname: "",
    username: "",
    email: ""
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const join = async () => {
    try {

      const res = await api.post("/auth/join", form);

      alert("회원가입 성공");

    } catch (e) {
      alert("회원가입 실패");
    }
  };

  return (
    <div>

      <h1>회원가입</h1>

      <input name="id" placeholder="아이디" onChange={handleChange} />
      <br />

      <input name="password" placeholder="비밀번호" type="password" onChange={handleChange} />
      <br />

      <input name="nickname" placeholder="닉네임" onChange={handleChange} />
      <br />

      <input name="username" placeholder="이름" onChange={handleChange} />
      <br />

      <input name="email" placeholder="이메일" onChange={handleChange} />
      <br />

      <button onClick={join}>회원가입</button>

    </div>
  );
}

export default Join;
