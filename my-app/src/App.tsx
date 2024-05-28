import React from 'react';
import { BrowserRouter as Router, Route, Switch, Redirect } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import Login from './components/Auth/Login';
import EmployeeList from './components/Employees/EmployeeList';
import EmployeeDetails from './components/Employees/EmployeeDetails';
import useAuthHook from './hooks/useAuth'

const PrivateRoute: React.FC<{ path: string; component: React.FC }> = ({ path, component }) => {
  const { isAuthenticated } = useAuthHook();
  return (
    <Route
      path={path}
      render={(props) =>
        isAuthenticated ? React.createElement(component, props) : <Redirect to="/login" />
      }
    />
  );
};

const App: React.FC = () => {
  return (
    <AuthProvider>
      <Router>
        <Switch>
          <Route path="/login" component={Login} />
          <PrivateRoute path="/employees/:id" component={EmployeeDetails} />
          <PrivateRoute path="/employees" component={EmployeeList} />
          <Redirect to="/employees" />
        </Switch>
      </Router>
    </AuthProvider>
  );
};

export default App;
