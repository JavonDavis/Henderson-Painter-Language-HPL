package hpl.sys;

import java.util.*;
import hpl.values.HPLFunction;

/**
 * An instance of class <code>HPLEnvironment</code> maintains a
 * collection of bindings from valid identifiers to T objects.
 * It supports storing and retrieving bindings, just as would be
 * expected in any dictionary.
 *
 * @author <a href="mailto:dcoore@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 * @param <T> The type of datum to be bound to variables in this environment
 */
public class HPLEnvironment<T> {

    HashMap<String, T> dictionary;
    HPLEnvironment<T> parent;
//    HashMap<String, HPLFunction> fnBindings;

    /**
     * Create a new (empty) top level HPL Environment.
     *
     */
    public HPLEnvironment() {
	parent = null;		// global by default
	dictionary = new HashMap<>();
//        fnBindings = new HashMap<>();
    }

    /**
     * Creates a new <code>HPLEnvironment</code> instance that is
     * initialised with the given collection of bindings (presented as
     * separate arrays of names and values), and extends the given
     * environment.
     *
     * @param p The parent environment that this one extends.
     * @param ids the collection of identifiers to be bound.
     * @param values the corresponding collection of values for the
     * identifiers.  Note that the two arrays must have the same length.
     */
    public HPLEnvironment(HPLEnvironment<T> p, ArrayList<String> ids, 
            ArrayList<T> values) {
        this();
	parent = p;
	for (int i = 0; i < ids.size(); i++) {
	    dictionary.put(ids.get(i), values.get(i));
	}
    }

    /**
     * Store a binding for the given identifier to the given T
     * object within this environment.
     *
     * @param id the name to be bound
     * @param value the value to which the name is bound.
     */
    public void put(String id, T value) {
	dictionary.put(id, value);
    }

    /**
     * Return the T object associated with the given identifier.
     *
     * @param id the identifier.
     * @return the T object associated with the identifier in
     * this environment.
     * @exception HPLException if <code>id</code> is unbound
     */
    public T get(String id) throws HPLException {
	T result =  dictionary.get(id);
	if (result == null)
            if (parent == null)
                throw new HPLException("Unbound variable " + id);
            else
                return parent.get(id);
	else
	    return result;
    }
//    
//    /**
//     * Store a binding to a function.
//     * @param id The name of the function
//     * @param fn The function object to be bound.
//     */
//    public void putFn(String id, HPLFunction fn) {
//        fnBindings.put(id, fn);
//    }
//    
//    /**
//     * Retrieve the function bound to the given name.
//     * @param id The name of the function
//     * @return The value of the nearest binding for the given name in the 
//     * function name space.
//     * @throws HPLException if no function is bound to the given name either in
//     * this frame's function namespace or in that of any of its ancestors.
//     */
//    public HPLFunction getFn(String id) throws HPLException {
//        HPLFunction result = fnBindings.get(id);
//        if (result == null)
//            if (parent == null)
//                throw new HPLException("Unbound function " + id);
//            else
//                return parent.getFn(id);
//        else
//            return result;
//    }

    /**
     * Create a string representation of this HPL environment.
     *
     * @return a string of all the names bound in this environment.
     */
    @Override
    public String toString() {
	StringBuffer result = new StringBuffer();

	for (String name : dictionary.keySet()) {
	    result = result.append(name);
	    result = result.append(" ");
	}
	return result.toString();
    }

}
