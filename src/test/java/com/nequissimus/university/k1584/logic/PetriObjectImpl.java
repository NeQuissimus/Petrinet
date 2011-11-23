// CHECKSTYLE:OFF
package com.nequissimus.university.k1584.logic;

final class PetriObjectImpl extends PetriObject {

    PetriObjectImpl(final String name, final String id) {
        super(name, id);
    }

    @Override
    protected PetriObject clone() {
        throw new NoSuchMethodError();
    }

}
