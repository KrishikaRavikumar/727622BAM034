const PostCard = ({ post }) => (
    <div className="bg-white rounded-xl shadow p-4 mb-4">
      <div className="flex items-center gap-4 mb-2">
        <img src={post.userImage} alt={post.userName} className="w-10 h-10 rounded-full" />
        <div>
          <h5 className="font-medium">{post.userName}</h5>
          <p className="text-xs text-gray-400">{new Date(post.timestamp).toLocaleString()}</p>
        </div>
      </div>
      <p className="mb-2">{post.content}</p>
      <img src={post.image} alt="post" className="rounded-md" />
      <p className="mt-2 text-sm text-gray-500">Comments: {post.commentCount}</p>
    </div>
  );
  
  export default PostCard;