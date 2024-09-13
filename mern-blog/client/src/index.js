import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';

import './index.css'
import Layout from './components/Layout';
import Errorpage from './pages/Errorpage';
import Home from './pages/Home'
import PostDetail from './pages/PostDetail';
import Register from './pages/Register';
import Login from './pages/Login';
import UserProfile from './pages/UserProfile';
import Autors from './pages/Autors';
import EditPost from './pages/EditPost';
import AuthorPost from './pages/AuthorPost';
import Logout from './pages/Logout';
import Dashboard from './pages/Dashboard';
import CreatePost from './pages/CreatePost';
import CategoryPosts from './pages/Categorypost';


const router = createBrowserRouter([
  {
    path:"/",
    element: <Layout/>,
    errorElement: <Errorpage/>,
    children: [
       {index: true, element: <Home />},
       {path: "posts/:id", element: <PostDetail />},
       {path: "register", element: <Register />},
    {path: "login", element: <Login />},
    {path: "profile/:id", element: <UserProfile />},
    {path: "authors", element: <Autors />},
    {path: "create", element: <CreatePost />},
    {path: "posts/categories/:category", element: <CategoryPosts/>},
    {path: "posts/users/:id", element: <AuthorPost />},
    {path: "myposts/:id", element: <Dashboard />},
    {path: "posts/:id/edit", element: <EditPost />},
    {path: "logout", element: <Logout />},
    ]
  }
])

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RouterProvider router={router}/>
  </React.StrictMode>
);
