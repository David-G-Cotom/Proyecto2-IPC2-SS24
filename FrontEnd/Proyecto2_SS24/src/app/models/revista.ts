export class Revista {

    puedeComentarse: boolean;
    puedeTenerLikes: boolean;
    puedeSuscribirse: boolean;
    descripcion: string;
    categoria: string;
    etiquetas: string[];
    idUsuario: number;
    likes: number;
    comentarios: string[];
    costo: number;
    fechaCreacion: string;
    suscripciones: string[];
    nombre: string;
    idRevista: number;
    costoGlobal: number;

    constructor (puedeComentarse: boolean, puedeTenerLikes: boolean, puedeSuscribirse: boolean, descripcion: string,
        categoria: string, etiquetas: string[], idUsuario: number, likes: number, comentarios: string[],
        costo: number, fechaCreacion: string, suscripciones: string[], nombre: string, idRevista: number,
        costoGlobal: number
    ) {
        this.puedeComentarse = puedeComentarse;
        this.puedeTenerLikes = puedeTenerLikes;
        this.puedeSuscribirse = puedeSuscribirse;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.etiquetas = etiquetas;
        this.idUsuario = idUsuario;
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