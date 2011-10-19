package com.nequissimus.university.k1584.logic;

import java.util.ArrayList;
import java.util.List;

public class PetriSnapshots {

    private final List<PetriNet> nets = new ArrayList<PetriNet>();
    
    public PetriNet getCurrent() {
	
	return this.nets.get(this.nets.size() - 1);
	
    }
    
    public PetriNet getByName(final String name) {
	
	for (PetriNet net : this.nets) {
	    
	    if (net.getName().equals(name))
		return net;
	    
	}
	
	return null;
	
    }
    
    public void delete(PetriNet net) {
	
	this.nets.remove(net);
	
    }    
    
    public PetriNet add(final String name) {
	
	PetriNet net = new PetriNet(name);
	this.nets.add(net);
	
	return net;
	
    }    
    
    public void rename(final PetriNet net, final String newName) {
	
	net.setName(newName);
	
    }
    
    public List<PetriNet> getNets() {
	
	return this.nets;
	
    }
    
}
