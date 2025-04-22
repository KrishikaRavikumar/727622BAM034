import { useEffect, useState } from 'react';
import api from '../api/api';
import PostCard from '../components/PostCard';

const Feed = () => {
  const [posts, setPosts] = useState([]);

  const fetchPosts = async () => {
    const res = await api.get('/posts');
    setPosts(res.data);
  };

  useEffect(() => {
    fetchPosts();
    const interval = setInterval(fetchPosts, 5000);
    return () => clearInterval(interval);
  }, []);

  return (
    <div>
      <h2 className="text-2xl font-bold mb-4">Real-Time Feed</h2>
      {posts.map(post => <PostCard key={post.id} post={post} />)}
    </div>
  );
};

export default Feed;
