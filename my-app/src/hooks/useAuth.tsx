import { useAuth } from '../context/AuthContext';

const useAuthHook = () => {
  const { isAuthenticated, login, logout } = useAuth();

  const authenticate = async (credentials: { username: string, password: string }) => {
    try {
      const { token } = await login(credentials);
      login(token);
    } catch (error) {
      throw new Error('Authentication failed');
    }
  };

  return { isAuthenticated, authenticate, logout };
};

export default useAuthHook;
