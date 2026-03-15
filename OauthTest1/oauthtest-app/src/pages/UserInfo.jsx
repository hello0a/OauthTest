import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../apis/api";

function UserInfo() {

  const { id } = useParams();

  const [user, setUser] = useState(null);

  useEffect(() => {

    const fetchUser = async () => {

      const res = await api.get(`/auth/userInfo/${id}`);

      setUser(res.data);

    };

    fetchUser();

  }, [id]);

  if (!user) return <div>loading...</div>;

  return (
    <div>

      <h1>회원 정보</h1>

      <p>UUID : {user.uuid}</p>
      <p>ID : {user.id}</p>
      <p>닉네임 : {user.nickname}</p>
      <p>이름 : {user.username}</p>
      <p>이메일 : {user.email}</p>
      <p>Provider : {user.provider}</p>

    </div>
  );
}

export default UserInfo;
