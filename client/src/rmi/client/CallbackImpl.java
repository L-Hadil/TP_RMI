package rmi.client;

import rmi.commons.IClientCallback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallbackImpl extends UnicastRemoteObject implements IClientCallback {

    public CallbackImpl() throws RemoteException {
        super();
    }

    @Override
    public void notify(String message) throws RemoteException {
        System.out.println("Notification reçue: " + message);
    }

}
