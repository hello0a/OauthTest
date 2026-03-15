import { Link } from "react-router-dom";

function Home() {
  return (
    <div>
      <h1>HOME</h1>

      <Link to="/login">로그인</Link>
      <br />

      <Link to="/join">회원가입</Link>

    </div>
  );
}

export default Home;
