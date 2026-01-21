import React from 'react';
import Menubar from './components/Menubar/Menubar.jsx'
import { Route, Routes } from 'react-router-dom';
import Dashboard from './pages/Dashboard/Dashboard.jsx';
import Explore from './pages/Explore/Explore.jsx';
import ManageUsers from './pages/ManageUsers/ManageUsers.jsx';
import ManageCategory from './pages/ManageCategory/ManageCategory.jsx';
import ManageItems from './pages/ManageItems/ManageItems.jsx';
import { Toaster } from 'react-hot-toast'; 


const App=()=>{
  return (
    <div>
      <Menubar />
      <div><Toaster /></div>
      <Routes>  
        <Route path="/" element={<Dashboard />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/explore" element={<Explore />} />
        <Route path="/users" element={<ManageUsers />} />
        <Route path="/category" element={<ManageCategory />} />
        <Route path="/items" element={<ManageItems />} />
      </Routes>
       
    </div>
  )
}

export default App;