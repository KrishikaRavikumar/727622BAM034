import { useEffect, useState } from 'react';
import api from '../api/api';
import PostCard from '../components/PostCard';

const TrendingPosts = () => {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    api.get('/trending-posts').then(res => setPosts(res.data));
  }, []);

  return (
    <div>
      <h2 className="text-2xl font-bold mb-4">Trending Posts</h2>
      {posts.map(post => <PostCard key={post.id} post={post} />)}
    </div>
  );
};

export default TrendingPosts;
