export class Revista {

    private puedeComentarse: boolean;
    private puedeTenerLikes: boolean;
    private puedeSuscribirse: boolean;
    private descripcion: string;
    private categoria: string;
    private etiquetas: string[];
    private idEditor: number;
    private likes: number;
    private comentarios: string[];
    private costo: number;
    private fechaCreacion: Date;
    private suscripciones: string[];
    private nombre: string;
    private idRevista: number;
    private costoGlobal: number;

    constructor (puedeComentarse: boolean, puedeTenerLikes: boolean, puedeSuscribirse: boolean, descripcion: string,
        categoria: string, etiquetas: string[], idEditor: number, likes: number, comentarios: string[],
        costo: number, fechaCreacion: Date, suscripciones: string[], nombre: string, idRevista: number,
        costoGlobal: number
    ) {
        this.puedeComentarse = puedeComentarse;
        this.puedeTenerLikes = puedeTenerLikes;
        this.puedeSuscribirse = puedeSuscribirse;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.etiquetas = etiquetas;
        this.idEditor = idEditor;
        this.likes = likes;
        this.comentarios = comentarios;
        this.costo = costo;
        this.fechaCreacion = fechaCreacion;
        this.suscripciones = suscripciones;
        this.nombre = nombre;
        this.idRevista = idRevista;
        this.costoGlobal = costoGlobal;
    }

}