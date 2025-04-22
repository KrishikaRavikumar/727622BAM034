import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import axios from "axios";

const Posts = () => {
  const [posts, setPosts] = useState([]);
  const location = useLocation();

  useEffect(() => {
    const query = new URLSearchParams(location.search);
    const type = query.get("type");
    axios.get(`http://localhost:8080/posts?type=${type}`)
      .then(res => setPosts(res.data))
      .catch(err => console.error(err));
  }, [location]);

  return (
    <div className="bg-white p-6 rounded shadow-md max-w-2xl mx-auto">
      <h2 className="text-xl font-bold mb-4">
        {new URLSearchParams(location.search).get("type") === "latest" ? "Latest Posts" : "Popular Posts"}
      </h2>
      <ul className="space-y-3">
        {posts.map(post => (
          <li key={post.id} className="p-3 border rounded">
            <p className="font-semibold">Post ID: {post.id}</p>
            <p>User ID: {post.userid}</p>
            <p>{post.content}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Posts;